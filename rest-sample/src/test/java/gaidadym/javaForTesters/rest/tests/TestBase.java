package gaidadym.javaForTesters.rest.tests;

import com.google.gson.Gson;
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

    private boolean isIssueOpen(String issueId) throws IOException {
        String json = getExecutor().execute( Request.Get("http://bugify.stqa.ru/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        String stateName = "";
        boolean state = false;
        for (JsonElement issue:issues.getAsJsonArray()){
            String id = "";
            id = issue.getAsJsonObject().get("id").getAsString();

            if (id.equals(issueId)){
                stateName = issue.getAsJsonObject().get("state_name").getAsString();
                if (stateName.equals("Open")){
                    state = true;
                }
                else{
                    state = false;
                }
            }

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
