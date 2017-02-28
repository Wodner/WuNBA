package wu.wunba.model;

import java.util.List;

/**
 * 描述：
 * 作者：Wu on 2017/2/28 16:45
 * 邮箱：wuwende@live.cn
 */

public class NBAPlayerData {

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    private String dataType;

    private List<String> head;
    private List<List<String>> rows;

    public List<String> getHead() {
        return head;
    }

    public void setHead(List<String> head) {
        this.head = head;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows) {
        this.rows = rows;
    }
}
