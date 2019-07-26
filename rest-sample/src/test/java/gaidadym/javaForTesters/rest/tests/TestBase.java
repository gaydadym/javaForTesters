package gaidadym.javaForTesters.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import gaidadym.javaForTesters.rest.model.Issue;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

import static com.sun.javafx.runtime.async.BackgroundExecutor.getExecutor;

public class TestBase {

    public boolean isIssueOpen(String issueId) throws IOException {
        String json = getExecutor().execute( Request.Get(String.format("http://bugify.stqa.ru/api/issues/%s.json",issueId)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonArray issues = parsed.getAsJsonObject().getAsJsonArray("issues");
        JsonElement issue = issues.get(0);
        String stateName = "";
        boolean state = false;
        stateName = issue.getAsJsonObject().get("state_name").getAsString();
        if (stateName.equals("Open")){
                state = true;
        }else{
                state = false;
        }

        return state;

    }
    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
    }

    public void skipIfNotFixed(String issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
