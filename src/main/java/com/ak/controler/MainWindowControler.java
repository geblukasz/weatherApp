package com.ak.controler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lukasz on 8/24/2017.
 */
public class MainWindowControler implements Initializable {

    private final static String surl = "http://api.apixu.com/v1/current.json?key=d578c483ed8b494bad8170534161511&q=Warsaw";
    private final static String coldImage = "http://www.personal.psu.edu/afr3/blogs/siowfa12/cold.jpg";
    private final static String hotImage = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQSmmmLN1JfEkCvdvbhIDsQw_xAJQ5QKjVxmQ0lXlq3pbJ-vts4";
    private final static String defaultImage = "https://www.heitkamp.senate.gov/public/_cache/files/c9eec4d7-0487-40b9-bc34-9cbf0b907081/nd-landscapes-11.jpg";

    //referencje na kontrolki

    @FXML
    private ImageView image;

    @FXML
    private Label temperature;

    /*@FXML
    private Label temperatureF;
*/
    public void initialize(URL location, ResourceBundle resources) {
        temperature.setText("Pobieranie danych pogody");
        try {
            URL url = new URL(surl);
            //jsonowy root
            JSONObject json = new JSONObject(IOUtils.toString(url));
            //chcemy pobrac zawartosc taga o nazwie "current"
            JSONObject json2 = (JSONObject) json.get("current");

            //pobieramy temperature w postaci tekstowej w *c
            String sstemp = json2.get("temp_c").toString();
            Double temp = Double.parseDouble(sstemp);

            //pobieram temperature w postaci tekstowej w F
            String ftemp = json2.get("temp_f").toString();
            Double tempF = Double.parseDouble(ftemp);
            Image im;
            if (temp < 15) {
                im = new Image(coldImage);
            } else if (temp >= 15 && temp < 22) {
                im = new Image(defaultImage);
            } else {
                im = new Image(hotImage);
            }
            if (im != null) {
                image.setImage(im);
            }

            temperature.setText("Srednia temperatura *C: " + temp);
            //temperatureF.setText("Srednia temperatura *C: " + ftemp);
            System.out.println(json.toString());

        } catch (MalformedURLException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
