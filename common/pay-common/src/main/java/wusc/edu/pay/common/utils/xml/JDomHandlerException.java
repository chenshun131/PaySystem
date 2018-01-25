package wusc.edu.pay.common.utils.xml;

public class JDomHandlerException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construct a new runtime com.chenshun.test.exception with <code>null</code> as its
     * detail message.
     */
    public JDomHandlerException() {
        super();
    }

    /**
     * Construct a new runtime com.chenshun.test.exception with the specified detail message.
     *
     * @param message
     *         The detail message for this com.chenshun.test.exception
     */
    public JDomHandlerException(String message) {
        this(message, null);
    }

    /**
     * Construct a new runtime com.chenshun.test.exception with the specified detail message
     * and cause.
     *
     * @param message
     *         The detail message for this com.chenshun.test.exception
     * @param cause
     *         The root cause for this com.chenshun.test.exception
     */
    public JDomHandlerException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * Construct a new runtime com.chenshun.test.exception with the specified cause and a
     * detail message of <code>(cause == null &#63; null : cause&#46;toString())</code>.
     *
     * @param cause
     *         The root cause for this com.chenshun.test.exception
     */
    public JDomHandlerException(Throwable cause) {
        super((cause == null) ? (String) null : cause.toString());
        this.cause = cause;
    }

    // ----------------------------------------------------- Instance Variables

    /**
     * The root cause of this com.chenshun.test.exception
     */
    protected Throwable cause = null;

    // --------------------------------------------------------- Public Methods

    /**
     * Return the root cause of this com.chenshun.test.exception (if any).
     */
    @Override
    public Throwable getCause() {
        return (this.cause);
    }

}
