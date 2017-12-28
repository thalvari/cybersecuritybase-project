package sec.project.config;

import org.apache.catalina.SessionIdGenerator;

public class MySessionIdGenerator implements SessionIdGenerator {

    private Integer id = 0;

    @Override
    public String getJvmRoute() {
        return null;
    }

    @Override
    public void setJvmRoute(String s) {

    }

    @Override
    public int getSessionIdLength() {
        return 0;
    }

    @Override
    public void setSessionIdLength(int i) {

    }

    @Override
    public String generateSessionId() {
        id++;
        return id.toString();
    }

    @Override
    public String generateSessionId(String s) {
        return null;
    }

}
