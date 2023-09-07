package id.fazzbca.news.exceptions.custom;

public class EntityFoundException extends RuntimeException{
    public EntityFoundException(String message) {
        super(message);
    }
}