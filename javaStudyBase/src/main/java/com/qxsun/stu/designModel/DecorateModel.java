
package com.qxsun.stu.designModel;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class DecorateModel {
    DataInputStream is = new DataInputStream(
            new BufferedInputStream(
                    new FileInputStream("")));

    public DecorateModel() throws FileNotFoundException {
    }
}
