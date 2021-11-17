/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.helper;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class XImages {
    public static Image getAppIcon() {
        URL url = XImages.class.getResource("/Image/favicon-32x32.png");
        return new ImageIcon(url).getImage();
    }
}
