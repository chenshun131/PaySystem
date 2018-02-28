package com.chenshun.springdata.test;

import com.chenshun.springdata.commonrepositorymethod.AddressRepository;
import com.chenshun.springdata.dao.PersonRepsotory;
import com.chenshun.springdata.domain.Person;
import com.chenshun.springdata.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * User: chenshun131 <p />
 * Time: 18/2/28 10:01  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SpringDataTest {

    private ApplicationContext ctx = null;

    private PersonRepsotory personRepsotory = null;

    private PersonService personService;

    {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        personRepsotory = ctx.getBean(PersonRepsotory.class);
        personService = ctx.getBean(PersonService.class);
    }

    @Test
    public void testCommonCustomRepositoryMethod() {
        ApplicationContext ctx2 = new ClassPathXmlApplicationContext("classpath:applicationContext2.xml");
        AddressRepository addressRepository = ctx2.getBean(AddressRepository.class);
        addressRepository.method();
    }

    @Test
    public void testCustomRepositoryMethod() {
        personRepsotory.test();
    }

    /**
     * 目标: 实现带查询条件的分页. id > 5 的条件
     * <p>
     * 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec, Pageable pageable);
     * Specification: 封装了 JPA Criteria 查询的查询条件
     * Pageable: 封装了请求分页的信息: 例如 pageNo, pageSize, Sort
     */
    @Test
    public void testJpaSpecificationExecutor() {
        int pageNo = 3 - 1;
        int pageSize = 5;
        PageRequest pageable = PageRequest.of(pageNo, pageSize);

        // 通常使用 Specification 的匿名内部类
        Specification<Person> specification = new Specification<Person>() {
            /**
             * @param *root: 代表查询的实体类.
             * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
             * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
             * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
             * @return: *Predicate 类型, 代表一个查询条件.
             */
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path path = root.get("id");
                return cb.gt(path, 5);
            }
        };

        Page<Person> page = personRepsotory.findAll(specification, pageable);

        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("当前第几页: " + (page.getNumber() + 1));
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前页面的 List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }

    @Test
    public void testJpaRepository() {
        Person person = new Person();
        person.setBirth(new Date());
        person.setEmail("xy@atguigu.com");
        person.setLastName("xyz");
        person.setId(28);

        Person person2 = personRepsotory.saveAndFlush(person);
        System.out.println(person == person2);
    }

    @Test
    public void testPagingAndSortingRespository() {
        // pageNo 从 0 开始
        int pageNo = 6 - 1;
        int pageSize = 5;
        // Pageable 接口通常使用的其 PageRequest 实现类，其中封装了需要分页的信息
        // Sort 封装排序的信息
        // Order 是具体针对于某一个属性进行升序还是降序
        Order order1 = new Order(Direction.DESC, "id");
        Order order2 = new Order(Direction.ASC, "email");
        Sort sort = new Sort(order1, order2);

        PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
        Page<Person> page = personRepsotory.findAll(pageable);

        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("当前第几页: " + (page.getNumber() + 1));
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("当前页面的 List: " + page.getContent());
        System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    }

    @Test
    public void testCrudReposiory() {
        List<Person> persons = new ArrayList<>();

        for (int i = 'a'; i <= 'z'; i++) {
            Person person = new Person();
            person.setAddressId(i + 1);
            person.setBirth(new Date());
            person.setEmail((char) i + "" + (char) i + "@atguigu.com");
            person.setLastName((char) i + "" + (char) i);

            persons.add(person);
        }

        personService.savePersons(persons);
    }

    @Test
    public void testModifying() {
//		personRepsotory.updatePersonEmail(1, "mmmm@atguigu.com");
        personService.updatePersonEmail("mmmm@atguigu.com", 1);
    }

    @Test
    public void testNativeQuery() {
        long count = personRepsotory.getTotalCount();
        System.out.println(count);
    }

    @Test
    public void testQueryAnnotationLikeParam() {
//		List<Person> persons = personRepsotory.testQueryAnnotationLikeParam("%A%", "%bb%");
//		System.out.println(persons.size());

//		List<Person> persons = personRepsotory.testQueryAnnotationLikeParam("A", "bb");
//		System.out.println(persons.size());

        List<Person> persons = personRepsotory.testQueryAnnotationLikeParam2("bb", "A");
        System.out.println(persons.size());
    }

    @Test
    public void testQueryAnnotationParams2() {
        List<Person> persons = personRepsotory.testQueryAnnotationParams2("aa@atguigu.com", "AA");
        System.out.println(persons);
    }

    @Test
    public void testQueryAnnotationParams1() {
        List<Person> persons = personRepsotory.testQueryAnnotationParams1("AA", "aa@atguigu.com");
        System.out.println(persons);
    }

    @Test
    public void testQueryAnnotation() {
        Person person = personRepsotory.getMaxIdPerson();
        System.out.println(person);
    }

    @Test
    public void testKeyWords2() {
        List<Person> persons = personRepsotory.getByAddress_IdGreaterThan(1);
        System.out.println(persons);
    }

    @Test
    public void testKeyWords() {
        List<Person> persons = personRepsotory.getByLastNameStartingWithAndIdLessThan("X", 10);
        System.out.println(persons);

        persons = personRepsotory.getByLastNameEndingWithAndIdLessThan("X", 10);
        System.out.println(persons);

        persons = personRepsotory.getByEmailInAndBirthLessThan(Arrays.asList("AA@atguigu.com", "FF@atguigu.com", "SS@atguigu.com",
                "1539831174@qq.com"), new Date());
        System.out.println(persons.size());
    }

    @Test
    public void testHelloWorldSpringData() {
        System.out.println(personRepsotory.getClass().getName());

        Person person = personRepsotory.getByLastName("AA");
        System.out.println(person);
    }

    @Test
    public void testJpa() {
    }

    @Test
    public void testDataSource() throws SQLException {
        // 检查 Spring 测试环境是否搭建成功
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

}
