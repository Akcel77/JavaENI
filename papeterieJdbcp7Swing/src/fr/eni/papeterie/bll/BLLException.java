package fr.eni.papeterie.bll;

public class BLLException extends Exception {

    public BLLException(){
        super();
    }

    public BLLException(String mess){
        super(mess);
    }

    public BLLException(String mess, Throwable exception){
        super(mess, exception);
    }

    @Override
    public String getMessage(){
        return "BLL error // " + super.getMessage();
    }
}
