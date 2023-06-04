package section4.lesson9;

import java.util.Optional;

public class OnlineClass {

private Integer id;

private String title;

private boolean closed;

public Progress progress;

public OnlineClass(Integer id, String title, boolean closed) {
    this.id = id;
    this.title = title;
    this.closed = closed;
}

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public boolean isClosed() {
    return closed;
}

public void setClosed(boolean closed) {
    this.closed = closed;
}

//public Progress getProgress() {
//    return progress;
//}

//자바 8 부터는 oprional 로 널처리를 가능하게 됐다.
public Optional<Progress> getProgress() {
    
    //ofNullable은 널일수 있다.
    //Of 면 null일 수 없다. 널일 시 예외 발생시킴
    return Optional.ofNullable(progress);
}

public void setProgress(Progress progress) {
    this.progress = progress;
}
}
