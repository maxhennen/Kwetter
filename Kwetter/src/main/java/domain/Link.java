package domain;

import java.io.Serializable;

public class Link implements Serializable {
    private String url;
    private String rel;

    public Link(){}

    public Link(String url, String rel) {
        this.url = url;
        this.rel = rel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
