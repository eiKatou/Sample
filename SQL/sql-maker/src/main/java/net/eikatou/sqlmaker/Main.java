package net.eikatou.sqlmaker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.nio.file.StandardOpenOption.*;

public class Main {
    private static int INSERT_NUM = 20;
    private static String INSERT = "insert into %s values(%s);";
    private static String TABLE_NAME = "SAMPLE1";
    private static int COL_NUM = 3;
    private static String[][] COL_VAL = {
            {"北海道","青森","岩手","宮城","秋田","山形","福島","茨城","栃木","群馬","埼玉","千葉","東京","神奈川","新潟","富山","石川","福井","山梨","長野","岐阜","静岡","愛知","三重","滋賀","京都","大阪","兵庫","奈良","和歌山","鳥取","島根","岡山","広島","山口","徳島","香川","愛媛","高知","福岡","佐賀","長崎","熊本","大分","宮崎","鹿児島","沖縄"},
            {"佐藤","鈴木","高橋","田中","渡辺","伊藤","山本","中村","小林","加藤","吉田","山田","佐々木","山口","松本","井上","斎藤","木村","林","清水","山崎","池田","阿部","森","橋本","山下","石川","中島","前田","藤田","小川","岡田","後藤","長谷川","村上","近藤","石井","坂本","遠藤","青木","藤井","西村","福田","太田","三浦","藤原","岡本","松田","斉藤","中川","中野","原田","小野","竹内","田村","金子","和田","中山","石田","上田","森田","原","柴田","酒井","工藤","横山","宮崎","宮本","内田","高木","安藤","谷口","大野","今井","丸山","高田","河野","藤本","小島","武田","村田","上野","杉山","増田","菅原","平野","小山","大塚","久保","千葉","松井","岩崎","野口","松尾","木下","菊地","野村","佐野","渡部","新井","杉本","大西","桜井","古川","市川","島田","小松","高野","水野","吉川","山内","西田","菊池","西川","北村","浜田","五十嵐","安田","中田","川口","平田","川崎","東","飯田","本田","久保田","吉村","辻","関","中西","福島","岩田","服部","樋口","川上","松岡","永井","山中","田口","森本","矢野","秋山","土屋","石原","松下","馬場","大橋","吉岡","松浦","小池","浅野","大久保","熊谷","荒木","野田","川村","星野","広瀬","大谷","黒田","尾崎","田辺","永田","松村","望月","堀","内藤","菅野","西山","大島","平井","岩本","片山","沢田","本間","早川","横田","荒井","岡崎","鎌田","大石","成田","小田","宮田","石橋","須藤","篠原","萩原","高山","小西","栗原","松原","伊東","三宅","福井","小沢","南","大森","奥村","片岡","内山","桑原","岡","富田","関口","松永","奥田","北川","古賀","上原","八木","吉野","白石","今村","川島","上村","小泉","中尾","青山","平山","牧野","寺田","渋谷","岡村","児玉","坂口","河合","大山","多田","小野寺","宮下","小倉","竹田","足立","小笠原","天野","村山","坂井","西","杉浦","坂田","小原","豊田","河村","武藤","角田","水谷","根本","関根","森下","中井","神田","田島","植田","塚本","佐久間","飯塚","前川","安部","浅井","山根","白井","宮川","岡部","大沢","大川","長田","堀内","稲垣","若林","松崎","榎本","森山","神谷","中沢","江口","中谷","畠山","谷","及川","細川","三上","今野","西尾","安達","田代","飯島","石塚","津田","岸本","荒川","中原","長尾","土井","本多","森川","三好","戸田","金井","米田","稲葉","岡野","村松","松山","佐伯","西岡","甲斐","星","中嶋","岩井","黒木","堤","金田","野崎","落合","堀田","泉","西野","町田","齋藤","山岸","新田","古田","徳永","黒川","滝沢","川田","山川","杉田","土田","笠原","金沢","奥山","三木","須田","梅田","村井","野中","堀江","大竹","川端","大村","岸","日高","梶原","広田","藤沢","西本","井口","向井","大木","大場","松島","竹中","榊原","藤川","吉本","川原","安井","大内","竹下","吉原","藤岡","庄司","塚田","福本","柳沢","高島","小谷","藤村","宮内","竹本","谷川","宇野","緒方","奥野","宮沢","窪田","下田","北野","栗田","石黒","亀井","長野","平川","藤野","宮原","長島","川本","茂木","三輪","下村","山村","丹羽","高井","古谷","吉沢","青柳","竹村","出口","荻野","嶋田","小森","黒沢","田原","高瀬","稲田","大城","筒井","福岡","横井","大平","宮城","大原","福永","林田","篠崎","富永","長岡","溝口","金城","山岡","平松","北原","浅田","越智","鶴田","武井","柳田","永野","大田","西沢","入江","湯浅","長沢","相馬","石山","園田","高松","堀川","二宮","臼井","手塚","沼田","川野","石崎","深沢","花田","比嘉","池上","篠田","平岡","谷本","小出","杉原","根岸","西原","笠井","瀬戸","田畑","野沢","片桐","浜口","大槻","志村","相沢","矢島","小坂","倉田","日野","福原","加納","千田","堀口","新谷","河原","松野","村瀬","徳田","菅","田上","森岡","冨田","吉井","柏木","島崎","北島","浜崎","浜野","内海","白川","中本","畑中","岩瀬","小柳","秋元","三谷","大崎","秋田","原口"},
            {"男性", "女性"}
    };

    public static void main(String[] args) throws IOException {
        validate();

        List<String> sqls = IntStream.range(0, INSERT_NUM)
                .mapToObj(s -> makeSql(s))
                .collect(Collectors.toList());

        // ファイル書き込み。IntelliJからの実行なら、このプロジェクトの直下にできる。
        Path outputFile = Paths.get("sql.txt");
        Files.write(outputFile, sqls, CREATE, WRITE, TRUNCATE_EXISTING);
    }

    private static void validate() {
        if (COL_VAL.length > COL_NUM) {
            throw new IllegalArgumentException("Column value pattern > column number.");
        }
    }

    private static String random(String[] values) {
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }

    private static String makeSql(int index) {
        List<String> colValues = new ArrayList<>();
        colValues.add(col1(index));
        colValues.add(col2(index));
        colValues.add(col3(index));
        colValues.add(col4(index));
        return String.format(INSERT, TABLE_NAME, String.join(",", colValues));
    }

    private static String col1(int index) {
        return Integer.toString(index);
    }

    private static String col2(int index) {
        return String.format("'%s'", random(COL_VAL[0]));
    }

    private static String col3(int index) {
        return String.format("'%s'", random(COL_VAL[1]));
    }

    private static String col4(int index) {
        return  String.format("'%s'", random(COL_VAL[2]));
    }

}