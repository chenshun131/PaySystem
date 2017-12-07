package wusc.edu.pay.common.web.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * <ul>
 * <li>Title:客户端Mac地址工具类</li>
 * <li>Description:</li>
 * <li>Copyright: www.gzzyzz.com</li>
 * <li>Company:</li>
 * </ul>
 *
 * @author Hill
 * @version 2014-7-22
 */
public class UdpGetClientMacAddr {

    private String sRemoteAddr;

    private int iRemotePort = 137;

    private byte[] buffer = new byte[1024];

    private DatagramSocket ds = null;

    public UdpGetClientMacAddr(String strAddr) throws Exception {
        sRemoteAddr = strAddr;
        ds = new DatagramSocket();
    }

    protected final DatagramPacket send(final byte[] bytes) throws IOException {
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(sRemoteAddr), iRemotePort);
        ds.send(dp);
        return dp;
    }

    protected final DatagramPacket receive() throws Exception {
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        ds.setSoTimeout(2000);
        ds.receive(dp);
        return dp;
    }

    private byte[] getQueryCmd() {
        byte[] tNs = new byte[50];
        tNs[0] = 0x00;
        tNs[1] = 0x00;
        tNs[2] = 0x00;
        tNs[3] = 0x10;
        tNs[4] = 0x00;
        tNs[5] = 0x01;
        tNs[6] = 0x00;
        tNs[7] = 0x00;
        tNs[8] = 0x00;
        tNs[9] = 0x00;
        tNs[10] = 0x00;
        tNs[11] = 0x00;
        tNs[12] = 0x20;
        tNs[13] = 0x43;
        tNs[14] = 0x4B;

        for (int i = 15; i < 45; i++) {
            tNs[i] = 0x41;
        }

        tNs[45] = 0x00;
        tNs[46] = 0x00;
        tNs[47] = 0x21;
        tNs[48] = 0x00;
        tNs[49] = 0x01;
        return tNs;
    }

    protected final String getMacAddr(byte[] brevdata) {
        int i = brevdata[56] * 18 + 56;
        String sAddr;
        StringBuilder sb = new StringBuilder(17);
        for (int j = 1; j < 7; j++) {
            sAddr = Integer.toHexString(0xFF & brevdata[i + j]);
            if (sAddr.length() < 2) {
                sb.append(0);
            }
            sb.append(sAddr.toUpperCase());
            if (j < 6) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public final void close() {
        try {
            ds.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public final String getRemoteMacAddr() throws Exception {
        byte[] bqcmd = getQueryCmd();
        send(bqcmd);
        DatagramPacket dp = receive();
        String smac = getMacAddr(dp.getData());
        close();
        return smac;
    }

    public static void main(String[] args) {
        try {
            UdpGetClientMacAddr upd = new UdpGetClientMacAddr("192.168.88.112");
            System.out.println(upd.getRemoteMacAddr());
        } catch (SocketTimeoutException e) {
            System.out.println("3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
