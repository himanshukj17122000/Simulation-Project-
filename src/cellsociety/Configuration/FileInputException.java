package cellsociety.Configuration;

public class FileInputException extends RuntimeException {
    private static final long serialVersionUID = 1L;


        /**
         * Create an exception based on an issue in our code.
         */
        public FileInputException (String message, Object ... values) {
            super(String.format(message, values));
        }

        /**
         * Create an exception based on a caught exception with a different message.
         */
        public FileInputException (Throwable cause, String message, Object ... values) {
            super(String.format(message, values), cause);
        }



        /**
         * Create an exception based on a caught exception, with no additional message.
         */
        public FileInputException (Throwable cause) {
            super(cause);
        }
    }


