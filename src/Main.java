import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类名 ClassName  Main
 * 项目 ProjectName  DelTXTDuplicateLine
 * 作者 Author  郑添翼 Taky.Zheng
 * 邮箱 E-mail 275158188@qq.com
 * 时间 Date  2019-07-15 19:10 ＞ω＜
 * 描述 Description TODO
 */
public class Main extends Application {

    File openFile;
    File saveFile;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button savebtn = new Button("保存目录");
        Button openbtn = new Button("选择文件");
        Label label = new Label();
        openbtn.setDisable(true);
        HBox hBox1 = new HBox(10,savebtn,openbtn,label);
        hBox1.setPadding(new Insets(10));


        primaryStage.setScene(new Scene(hBox1,300,50));
        primaryStage.setTitle("TXT文件重复行去除工具 1.0 Taky QQ:275158188");
        primaryStage.show();


        savebtn.setOnAction(p -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            saveFile  = directoryChooser.showDialog(primaryStage);
            if (saveFile == null) return;
            label.setText("准备转换...");
            openbtn.setDisable(false);
        });

        openbtn.setOnAction(p -> {
            FileChooser fileChooser = new FileChooser();
            openFile = fileChooser.showOpenDialog(primaryStage);
            if(openFile == null) return;
            label.setText("正在转换中...");
            try {
                List<String> strings = FileUtils.readLines(openFile, "utf-8");
                System.out.println(strings.size());
                List<String> list = strings.stream().distinct().collect(Collectors.toList());
                File newFile = new File(saveFile + "/" + openFile.getName());
                if (newFile.exists()) newFile.createNewFile();
                FileUtils.writeLines(newFile,list);
                label.setText("完成!");
                System.out.println(list.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });




    }

    public static void main(String[] args) {
        launch(args);
    }
}
