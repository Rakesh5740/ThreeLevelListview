package com.threelevellistview;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ExpandableClick {

    ExpandableListView expandableListView;
    ArrayList<MenuModel> alParent = new ArrayList<>();

    ArrayList<ArrayList<MenuModel>> alSecond = new ArrayList<>();
    ArrayList<MenuModel> almovies = new ArrayList<>();
    ArrayList<MenuModel> alGames = new ArrayList<>();


    //category has further genres
    ArrayList<MenuModel> alHorror = new ArrayList<>();
    ArrayList<MenuModel> alAction = new ArrayList<>();
    ArrayList<MenuModel> alThriller = new ArrayList<>();


    // games category has further genres
    ArrayList<MenuModel> alFps = new ArrayList<>();
    ArrayList<MenuModel> alMoba = new ArrayList<>();

    HashMap<MenuModel, ArrayList<MenuModel>> alThirdMovies = new HashMap<>();
    HashMap<MenuModel, ArrayList<MenuModel>> alThirdGames = new HashMap<>();

    //    The Data.
    ArrayList<HashMap<MenuModel, ArrayList<MenuModel>>> data = new ArrayList<>();

    HashMap<MenuModel, ArrayList<MenuModel>> alChildMap = new HashMap<>();
    private ArrayList<MenuModel> alSubChild = new ArrayList<>();
    private ArrayList<MenuModel> alSubChildNested=new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = findViewById(R.id.expandible_listview);

        /*One@@@@@@@@@@@@@@@@@@@@@@@*/
        alParent.add(new MenuModel("One", false, null));


        /*Two@@@@@@@@@@@@@@@@@@@@@@@*/
        alParent.add(new MenuModel("Two", true, null));
        alSubChild.add(new MenuModel("Sub111", true, null));
        alSubChildNested.add(new MenuModel("Newwww", false, null));
        alSubChildNested.add(new MenuModel("Newwww222", false, null));

        alSubChild.add(new MenuModel("Sub222", false, null));

        /*Movie@@@@@@@@@@@@@@@@@@@@@@@*/
        alParent.add(new MenuModel("Movie", true,
                getResources().getDrawable(R.drawable.ic_baseline_account_circle_24, null)));

        almovies.add(new MenuModel("Action", true,
                getResources().getDrawable(R.drawable.ic_baseline_add_comment_24, null)));
        alHorror.add(new MenuModel("Conjuring", true, null));
        alHorror.add(new MenuModel("Insidious", true, null));
        alHorror.add(new MenuModel("The Ring", true, null));

        almovies.add(new MenuModel("Horror", true, null));
        alAction.add(new MenuModel("Jon Wick", true, null));
        alAction.add(new MenuModel("Die Hard", true, null));
        alAction.add(new MenuModel("Fast 7", true, null));

        almovies.add(new MenuModel("Thriller", true, null));
        alThriller.add(new MenuModel("Imitation Game", true,
                getResources().getDrawable(R.drawable.ic_baseline_access_time_24, null)));
        alThriller.add(new MenuModel("Tinker", true, null));
        alThriller.add(new MenuModel("Tailer", true, null));


        /*Games@@@@@@@@@@@@@@@@@@@@@@@*/
        alParent.add(new MenuModel("Games", true, null));

        alGames.add(new MenuModel("Fps", true, null));
        alMoba.add(new MenuModel("Dota 2", true, null));
        alMoba.add(new MenuModel("League of Legends", true, null));

        alGames.add(new MenuModel("Moba", true, null));
        alFps.add(new MenuModel("Fast 7", true,
                getResources().getDrawable(R.drawable.ic_baseline_access_time_24, null)));


        // second level category names (genres)
        alSecond.add(null);
        alSecond.add(alSubChild);
        alSecond.add(almovies);
        alSecond.add(alGames);

        // Two category all data
        alChildMap.put(alSubChild.get(0), alSubChildNested);
        alChildMap.put(alSubChild.get(1), alSubChildNested);

        // movies category all data
        alThirdMovies.put(almovies.get(0), alHorror);
        alThirdMovies.put(almovies.get(1), alAction);
        alThirdMovies.put(almovies.get(2), alThriller);

        // games category all data
        alThirdGames.put(alGames.get(0), alFps);
        alThirdGames.put(alGames.get(1), alMoba);

        // all data
        data.add(null);
        data.add(alChildMap);
        data.add(alThirdMovies);
        data.add(alThirdGames);



        // set adapter
        expandableListView.setAdapter(
                new SecondLevelListAdapter(this, alParent,
                        alSecond, data, this));


        // OPTIONAL : Show one list at a time
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            Log.d("GroupClickListener:",""+groupPosition);
            if (!alParent.get(groupPosition).hasChildren) {
                onClick(alParent.get(groupPosition));
            }
            return false;
        });


    }

    @Override
    public void onClick(MenuModel model) {
        Toast.makeText(MainActivity.this,
                "click::: " + model.menuName,
                Toast.LENGTH_SHORT).show();
    }
}
