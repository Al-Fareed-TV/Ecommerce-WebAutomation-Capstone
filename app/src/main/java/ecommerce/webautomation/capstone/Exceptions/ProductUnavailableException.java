package ecommerce.webautomation.capstone.Exceptions;

public class ProductUnavailableException extends RuntimeException{
    public ProductUnavailableException(String message){
        super(message);
    }
}
