package com.chenshun.test.hadoop.io;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * User: mew <p />
 * Time: 18/2/27 13:41  <p />
 * Version: V1.0  <p />
 * Description: <p />
 */
public class UserWritable implements Writable {

    private int id;

    private String name;

    public UserWritable() {
    }

    public UserWritable(int id, String name) {
        this.set(id, name);
    }

    public void set(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(name);
    }

    public void readFields(DataInput in) throws IOException {
        this.id = in.readInt();
        this.name = in.readUTF();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserWritable other = (UserWritable) obj;
        if (id != other.id) {
            return false;
        }
        if (name == null) {
            return other.name == null;
        } else {
            return name.equals(other.name);
        }
    }

    @Override
    public String toString() {
        return id + "\t" + name;
    }

}
