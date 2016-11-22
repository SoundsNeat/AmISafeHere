package edu.csupomona.cs480.util.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author william
 */
public class MismatchedDataLoader {

    private List<MismatchedData> mismatchedDataList = new ArrayList<>();
    private static MismatchedDataLoader INSTANCE = null;

    private MismatchedDataLoader() {
        mismatchedDataList.add(new MismatchedData("New York City", "New York", "New York", "New York"));
        mismatchedDataList.add(new MismatchedData("Washington", "D.C.", "Washington", "District-of-Columbia"));
    }


    public static MismatchedDataLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MismatchedDataLoader();
        }
        return INSTANCE;
    }

    public List<MismatchedData> getMismatchedDataList() {
        return mismatchedDataList;
    }
}
