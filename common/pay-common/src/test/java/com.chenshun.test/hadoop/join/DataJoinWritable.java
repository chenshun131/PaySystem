package com.chenshun.test.hadoop.join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DataJoinWritable implements Writable {

    // makr,customer / oreder
    private String tag;

    // info
    private String data;

    public DataJoinWritable() {
        super();
    }

    public DataJoinWritable(String tag, String data) {
        set(tag, data);
    }

    public void set(String tag, String data) {
        this.tag = tag;
        this.data = data;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(tag);
        out.writeUTF(data);
    }

    public void readFields(DataInput in) throws IOException {
        this.tag = in.readUTF();
        this.data = in.readUTF();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
        DataJoinWritable other = (DataJoinWritable) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (tag == null) {
            return other.tag == null;
        } else {
            return tag.equals(other.tag);
        }
    }

    @Override
    public String toString() {
        return tag + "," + data;
    }

}
