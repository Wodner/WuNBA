package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.model.NBADataSort;
import wu.wunba.ui.NBADataSortPresenter;
import wu.wunba.ui.activity.NBAPlayerDetialActivity;
import wu.wunba.ui.adapter.NBADataSortAdapter;
import wu.wunba.ui.view.NBADataSortView;
import wu.wunba.ui.widget.BasketballLoading;

import static wu.wunba.ui.NBADataSortPresenter.TYPE_STAT_POINT;
import static wu.wunba.ui.NBADataSortPresenter.TYPE_TAB_DAY;

/**
 * 描述：数据排行
 * 作者：Wu on 2017/2/25 21:28
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.fragment_nba_data_sort)
public class NBADataSortFragment extends BaseFragment implements NBADataSortView,NBADataSortAdapter.OnRecyclerViewItemClickListener{

    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerView;

    @ViewInject(R.id.spinner_tab)
    Spinner spinnerTab;
    @ViewInject(R.id.spinner_stat)
    Spinner spinnerStat;

    private int CURRENT_TAB_TYPE = TYPE_TAB_DAY;
    private int CURRENT_STAT_TYPE= TYPE_STAT_POINT;

    private NBADataSortPresenter dataSortPresenter;
    private NBADataSortAdapter dataSortAdapter;
    public static NBADataSortFragment nbaDataFragment;

    public static NBADataSortFragment getInstance(){
        if(nbaDataFragment == null){
            nbaDataFragment = new NBADataSortFragment();
        }
        return nbaDataFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataSortAdapter = new NBADataSortAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(dataSortAdapter);
        dataSortAdapter.setOnItemClickListener(this);
        dataSortPresenter = new NBADataSortPresenter(getActivity(),this);
        dataSortPresenter.initialized(0);
        spinnerTab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CURRENT_TAB_TYPE = i;
                ballLaodingDismiss();
                dataSortPresenter.getNBADataSort(CURRENT_TAB_TYPE,CURRENT_STAT_TYPE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        spinnerStat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CURRENT_STAT_TYPE = i;
                ballLaodingDismiss();
                dataSortPresenter.getNBADataSort(CURRENT_TAB_TYPE,CURRENT_STAT_TYPE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }


    @Override
    public void onItemClick(View v, NBADataSort nbaDataSort, int postion) {
        Bundle bundle = new Bundle();
        bundle.putString(Config.PLAYER_ID,nbaDataSort.getPlayerId());
        NBAPlayerDetialActivity.startAction(getActivity(),bundle);
    }

    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(BasketballLoading.TYPE_QUAN);
    }

    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
    }

    @Override
    public void showError(String msg) {
        ballLaodingDismiss();
    }


    @Override
    public void showDataSort(List<NBADataSort> dataSortList) {
        Logger.d("得到数据 ： " + dataSortList.size());
        dataSortAdapter.setData(dataSortList);
    }
}
