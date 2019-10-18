package fag.edu.com.gerenciadordefichadeaviario.models;

public class Result {
    private String content;
    private boolean error;

    public Result(String content, boolean error) {
        this.content = content;
        this.error = error;
    }

    public Result() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Result{" +
                "content='" + content + '\'' +
                ", error=" + error +
                '}';
    }
}

