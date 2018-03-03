package wusc.edu.pay.app.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import wusc.edu.pay.app.notify.core.NotifyQueue;
import wusc.edu.pay.app.notify.core.NotifyTask;
import wusc.edu.pay.common.page.PageBean;
import wusc.edu.pay.common.page.PageParam;
import wusc.edu.pay.facade.notify.entity.NotifyRecord;
import wusc.edu.pay.facade.notify.service.NotifyFacade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.DelayQueue;


/**
 * 通知App
 *
 * @author zws
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static DelayQueue<NotifyTask> tasks = new DelayQueue<>();

    private static ClassPathXmlApplicationContext context;

    private static ThreadPoolTaskExecutor threadPool;

    public static NotifyFacade notifyFacade;

    private static NotifyQueue notifyQueue;

    public static void main(String[] args) {
        try {
            context = new ClassPathXmlApplicationContext(new String[]{"spring1/spring1-context.xml"});
            context.start();
            threadPool = (ThreadPoolTaskExecutor) context.getBean("threadPool");
            notifyFacade = (NotifyFacade) context.getBean("notifyFacade");
            notifyQueue = (NotifyQueue) context.getBean("notifyQueue");
            startInitFromDB();
            startThread();
            logger.info("== context start");
        } catch (Exception e) {
            logger.error("== application start error:", e);
            return;
        }
        synchronized (App.class) {
            while (true) {
                try {
                    App.class.wait();
                } catch (InterruptedException e) {
                    logger.error("== synchronized error:", e);
                }
            }
        }
    }

    private static void startThread() {
        // Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
        // public void run() {
        // System.out.println(threadPool.getActiveCount() + "-->current active--->" + tasks.size() + "max is--->" +
        // threadPool.getMaxPoolSize());
        // }
        // }, 0, 5, TimeUnit.SECONDS);

        threadPool.execute(() -> {
            try {
                while (true) {
                    // 如果当前活动线程等于最大线程，那么不执行
                    if (threadPool.getActiveCount() < threadPool.getMaxPoolSize()) {
                        final NotifyTask task = tasks.poll();
                        if (task != null) {
                            threadPool.execute(() -> {
                                System.out.println(threadPool.getActiveCount() + "---------");
                                tasks.remove(task);
                                task.run();
                            });
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 从数据库中取一次数据用来当系统启动时初始化
     */
    private static void startInitFromDB() {
        logger.info("get data from database");

        int pageNum = 1;
        int numPerPage = 500;
        PageParam pageParam = new PageParam(pageNum, numPerPage);

        // 查询状态和通知次数符合以下条件的数据进行通知
        String[] status = new String[]{"101", "102", "200", "201"};
        Integer[] notifyTime = new Integer[]{0, 1, 2, 3, 4};

        // 组装查询条件
        Map<String, Object> paramMap = new HashMap<>(2);
        paramMap.put("statusList", status);
        paramMap.put("notifyTimeList", notifyTime);

        PageBean pageBean = notifyFacade.queryNotifyRecordListPage(pageParam, paramMap);
        while (pageNum <= pageBean.getEndPageIndex()) {
            List<Object> list = pageBean.getRecordList();
            for (Object aList : list) {
                NotifyRecord notifyRecord = (NotifyRecord) aList;
                notifyRecord.setLastNotifyTime(new Date());
                notifyQueue.addElementToList(notifyRecord);
            }
            pageNum++;
            logger.info(String.format("调用通知服务.notifyFacade.notiFyReCordListPage(%s, %s, %s)", pageNum, numPerPage,
                    paramMap));
            pageBean = notifyFacade.queryNotifyRecordListPage(pageParam, paramMap);
        }
    }

}
