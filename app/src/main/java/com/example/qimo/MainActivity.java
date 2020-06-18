package com.example.qimo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<News> newsList=new ArrayList<>();

    private DrawerLayout mDrawerLayout;

    private Button showPriorityNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        展示toolbat
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 初始化新闻内容
//        LitePal.getDatabase();
//        LitePal.deleteAll(News.class);
//        addNews();
        initNews();
//        对新闻内同进行展示
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter=new NewsAdapter(newsList);
        recyclerView.setAdapter(adapter);

        //        登录之后左边的滑动菜单
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView) findViewById(R.id.nav_view);
//        点击按钮显示重点新闻列表
        showPriorityNews=(Button) findViewById(R.id.showPriorityNews);

        showPriorityNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PriorityNewsList.class);
                startActivity(intent);
            }
        });
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
//            让导航按钮显示
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);
//        设置点击所有滑动菜单上面的选项都关闭滑动菜单
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
//        使用悬浮按钮作为增加新闻的按钮,点击启动增加新闻活动
        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddNewsActivity.class);
                startActivity(intent);
            }
        });
    }

//    设置菜单栏
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
//    设置菜单栏上面的按钮点击事件
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Warning");
                dialog.setMessage("You hava to login!");
                dialog.setCancelable(false);
                dialog.setPositiveButton("login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("cansel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
                break;

            case R.id.delete:
                Toast.makeText(this, "You click Delete", Toast.LENGTH_SHORT).show();
                break;

            case R.id.settings:
                Toast.makeText(this, "You click settings", Toast.LENGTH_SHORT).show();
                break;

            default:
        }
        return true;
    }

    public void addNews(){
        News news1 = new News();
        news1.setImageId(R.drawable.fish);
        news1.setTitle("黑龙江黑河：渔民误捕300斤大鳇鱼 人工产卵后将放生");
        news1.setAuthor("2020年06月17日05:41  来源：人民网－人民日报");
        news1.setContent("近日，黑龙江省黑河市爱辉区幸福乡渔民在黑龙江上正常作业时，误捕到一条重约300斤的达氏鳇，爱辉区农业综合行政执法大队渔政中队即刻采取措施，实施救助，将它运送到黑河华之宝鲟鱼有限公司鲟鳇鱼救助中心进行救治保护，当时该鱼鱼背鳍有划伤，腮腺出血。目前，这条达氏鳇生命体征平稳，正在救助中心精心养伤，等待进行人工产卵后放生回归。\n" +
                "\n" +
                "据了解，这位特殊的来客是一条野生雌性长290厘米、体重约300斤的达氏鳇，年龄在60至70岁。达氏鳇俗称鳇鱼，是淡水鱼类中体形最大的一种，也是全世界唯有黑龙江流域才有的珍贵鱼种，是白垩纪时期保存下来的古生物群之一，具有“水中熊猫”的美称，为国家二级野生保护动物。人民图片/张辉 摄");
        newsList.add(news1);
        news1.save();
        News news2=new News("一山一水总关情 感悟习近平总书记宁夏行的人民情怀","方开燕、贾茹、邓志慧、宋子节\n" +
                "\n" +
                "2020年06月17日09:24  来源：人民网-宁夏频道","初夏时节，习近平总书记在宁夏考察。这是总书记今年全国两会后的首次国内考察活动，也是党的十八大以来第二次来到宁夏。\n" +
                "\n" +
                "在党的十九大报告中，习近平提出：“人与自然是生命共同体，人类必须尊重自然、顺应自然、保护自然。”“我们要建设的现代化是人与自然和谐共生的现代化。”“人与自然是生命共同体”的重要论断，立意高远，内涵丰富，思想深刻。\n" +
                "\n" +
                "从1997年到2020年，20多年间，习近平4次踏入宁夏这片土地。回顾他的考察足迹，始终离不开以民为本的一水、一沙、一河、一山，让广大人民群众感受到他“人民至上”的深厚情怀。\n" +
                "\n" +
                "水：寻“生命之源” 解贫困之围\n" +
                "\n" +
                "“把天上水、地表水、地下水都利用好”\n" +
                "\n" +
                "1997年4月，作为福建省委副书记，同时也是福建省对口帮扶宁夏领导小组组长的习近平第一次来到宁夏。经过6天时间翻山越沟，习近平被西海固的极端贫困深深震撼，下决心贯彻党中央决策部署，推动福建和宁夏开展对口帮扶。\n" +
                "\n" +
                "西海固位于宁夏中南部地区，十年九旱，自然条件严酷，素有“苦瘠甲天下”之称。“水贵如油”。缺水，影响到了百姓的生存生活，也是贫困地区发展生产的最大制约因素。\n"+"\n"+"当年，习近平对口帮扶宁夏脱贫攻坚的一个重点就是做好水的文章。他抓井窖工程，解决群众生活用水问题；鼓励小圆井抽水灌溉，解决产业用水问题；通过生态移民搬迁，恢复植被、涵养水土……习近平说:“干旱地区主要是缺水的问题，就是要这样因地制宜解决，根据不同的条件，把天上水、地表水、地下水都利用好。”\n" +
                "\n" +
                "2016年7月18日，习近平来到泾源县大湾乡杨岭村看望父老乡亲，实地考察精准扶贫情况。在考察中，除了察看屋内陈设外，习近平尤其留意村民家的淋浴设施。听说安了太阳能热水器，习近平说“挺好”，关心地问家里的小男孩：“你常洗澡吗？”\n" +
                "\n" +
                "解决好老百姓吃水用水难题，习近平念兹在兹，无日他忘。在习近平亲自部署推进下，闽宁协作的种子逐渐生根、发芽，有些已经结出了累累硕果。23年来，福建省本着“宁夏所需，福建所能”，源源不断向宁夏贫困地区投入帮扶资金，在严重缺水的乡村打井窖，修建高标准梯田，解决了30万人、10余万头大牲畜的饮水困难问题。西海固地区做活了水的文章，初步解决了生活用水、产业用水的问题，并逐步实现开发用水和生态蓄水的良性循环。\n" +
                "\n" +
                "水，不仅解决了老百姓生活生产的问题，种活了庄稼，更浇灌着老百姓摘掉“穷帽子”的希望种子。如今，在西海固地区的很多村庄，几乎家家都已通上自来水。\n" +
                "\n" +
                "习近平始终坚持人民至上、生命至上，时刻把人民群众的安危冷暖放在心上。他当年推动的那些扶贫措施，改变了无数西海固贫困家庭的命运。正如总书记期望的那样，这里正在由“干沙滩”变成“金沙滩”。\n" +
                "\n" +
                "沙：缚住“黄龙” 人与沙和谐共处\n" +
                "\n" +
                "“你们在这里做出了很大贡献，非常了不起”\n" +
                "\n" +
                "远离干旱、风沙、贫困，是萦绕在西北人心头的梦想，也是习近平心中深深的牵挂。\n" +
                "\n" +
                "昔日的宁夏灵武白芨滩林场黄沙漫天。因西、北、东三面分别毗邻腾格里沙漠、乌兰布和沙漠和毛乌素沙地，很长一段时间里，宁夏人的记忆都是“一年一场风，从春刮到冬；风吹沙子走，抬脚不见踪”。白芨滩国家级自然保护区管理局党委原书记王有德不甘心，带领职工在流动沙丘固沙造林，立誓与沙漠抗争。\n" +
                "\n" +
                "“养个娃娃容易，在沙漠里种棵树难。”此话不假，治沙人千辛万苦栽好的树苗，常常一夜之间就被风沙埋葬，但他们还是不停地补栽，直到树木连成片，把黄沙牢牢锁住……\n" +
                "\n" +
                "不负韶华,未来可期。昔日沙洲变成了真正的绿洲。如今，春意盎然的灵武白芨滩国家级自然保护区里，沙丘上长着柠条、花棒、沙柳等植物，诉说着治沙英雄王有德与治沙人在风雨中固沙造林的动人故事，也书写着宁夏治沙奇迹，展现着人类的顽强不屈。\n" +
                "\n" +
                "2008年，时任国家副主席的习近平来到宁夏，专程看望了王有德和他的同事们。面对一望无际的麦草方格，他紧紧握住王有德的手说：“我是来向你学习的。你们在这里做出了很大贡献，非常了不起。”\n" +
                "\n");
        news2.setImageId(R.drawable.ningxia);
        newsList.add(news2);
        news2.save();
        News news3=new News("山西运城出现持续性强降雨 交警冒雨保畅通","人民网","2020年6月16日，山西省和运城市气象台连续发布持续暴雨蓝色预警，运城市多地出现持续性强降雨，致城乡多条国道、省道和城区出现严重积水，道路交通受阻。当地交警部门立即启动恶劣天气道路交通安全预案，交警、辅警全警出动查隐患，指挥疏通交通，确保了恶劣天气道路畅通，无重特大道路交通事故发生。人民图片/高新生 摄");
        news3.setImageId(R.drawable.traffic_police);
        newsList.add(news3);
        news3.save();

        News news4=new News("四川丹巴县梅龙沟发生泥石流 阻断小金川河 形成堰塞湖","人民网","人民网成都6月17日电 （朱虹）6月17日凌晨3点20分许，四川甘孜州丹巴县半扇门镇梅龙沟发生泥石流，阻断小金川河，形成堰塞湖，造成G350烂水湾段道路中断，烂水湾阿娘寨村山体滑坡。\n" +
                "\n" +
                "截至6月17日6：00，堰塞湖险情威胁下游6个乡镇17个村、4所学校、3所卫生院、2座寺庙。目前共疏散5800余人，其中：群众4700余人，师生1100余人。据初步统计，受困人员15人，乡村两级救援队伍已成功救援8人，6人正在施救中，1人失联。");
        news4.setImageId(R.drawable.nishiliu);
        newsList.add(news4);
        news4.save();
    }

    public void initNews(){
        newsList= LitePal.findAll(News.class);
    }
}
