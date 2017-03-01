package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.ui.activity.NBAImportanceDayActivity;
import wu.wunba.ui.activity.NBATeamActivity;

/**
 * 描述：
 * 作者：Wu on 2017/2/17 22:56
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.fragment_nba_more)
public class NbaMoreFragment extends BaseFragment {

    @ViewInject(R.id.rl_title_team)
    RelativeLayout rlTitleTeam;


    public static NbaMoreFragment nbaMoreFragment;

    public static NbaMoreFragment getInstance(){
        if(nbaMoreFragment == null){
            nbaMoreFragment = new NbaMoreFragment();
        }
        return nbaMoreFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Event(type = View.OnClickListener.class,value = R.id.rl_title_team)
    private void teamOnClick(View v){
        NBATeamActivity.startAction(getActivity());
    }

    @Event(type = View.OnClickListener.class,value = R.id.rl_title_player)
    private void playerOnClick(View v){
        Toast.makeText(getActivity(),"球员",Toast.LENGTH_LONG).show();
    }

    @Event(type = View.OnClickListener.class,value = R.id.rl_title_team_schedule)
    private void teamScheduleOnClick(View v){
        Toast.makeText(getActivity(),"球队赛程",Toast.LENGTH_LONG).show();
    }

    @Event(type = View.OnClickListener.class,value = R.id.rl_title_important_day)
    private void ImportantDayOnClick(View v){
        NBAImportanceDayActivity.startAction(getActivity());
    }
    @Event(type = View.OnClickListener.class,value = R.id.rl_title_today_nba)
    private void todayNBAOnClick(View v){
        Toast.makeText(getActivity(),"天天NBA",Toast.LENGTH_LONG).show();
    }
    @Event(type = View.OnClickListener.class,value = R.id.rl_title_nba_store)
    private void storeOnClick(View v){
        Toast.makeText(getActivity(),"NBA Store",Toast.LENGTH_LONG).show();
    }






}
