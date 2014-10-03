/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.technovision.technokitap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFImageWriter;

/**
 *
 * @author balancy
 */
public class Book {

    private final ArrayList<String> paths;
    public String name, path, data, sound, video, images, activeImages, pages, big, thumbs;

    public Book(String bookName, String bookPath) {
        paths = new ArrayList<>();
        name = bookName;
        path = bookPath + bookName + File.separator;
        data = path + "Data" + File.separator;
        sound = path + "Sound" + File.separator;
        video = path + "Video" + File.separator;
        images = path + "Images" + File.separator;
        activeImages = images + "ActiveImages" + File.separator;
        pages = path + "Images" + File.separator;
        big = pages + "big" + File.separator;
        thumbs = pages + "thumbs" + File.separator;

        paths.add(path);
        paths.add(data);
        paths.add(sound);
        paths.add(video);
        paths.add(images);
        paths.add(activeImages);
        paths.add(pages);
        paths.add(big);
        paths.add(thumbs);
    }

    /**
     * Packagify
     *
     */
    public void packagify() {
        // 1. create Book folders and subfolders
        for (String str : paths) {
            try {
                System.out.println(str);
                new File(str).mkdirs();
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
        //TODO 2. create sample Main.xml in Data folder
        //TODO 3. create Book.txt                
    }

    /**
     * Imagify
     *
     * @param pdfFile
     */
    public void imagify(String pdfFile) {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            int imageType = BufferedImage.TYPE_INT_RGB;
            PDFImageWriter pw = new PDFImageWriter();

            boolean success = pw.writeImage(document, "jpg", "",
                    1, document.getNumberOfPages(), big, imageType, 300);
            if (!success) {
                System.err.println("Error: no writer found for image format");
                System.exit(1);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * Copies images in big folder to thumbs folder
     */
    public void copyBig2Thumbs() {
        File sourceDir = new File(big); //this directory already exists
        File destDir = new File(thumbs);

        Path destPath = destDir.toPath();
        for (File sourceFile : sourceDir.listFiles()) {
            Path sourcePath = sourceFile.toPath();
            try {
                Files.copy(sourcePath, destPath.resolve(sourcePath.getFileName()));
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    /**
     * Rename
     *
     * @param path
     */
    public void rename(String path) {
        //TODO rename file in given path
    }

    /**
     * Resize images
     *
     * @param path
     */
    public void resize(String path) {
        if (path.endsWith("big")) {
            //TODO resize resolution to 72 and height to 1400px
        } else if (path.endsWith("thumbs")) {
            //TODO resize resolution to 72 and height to 500px
        }
    }

    /**
     * Encode/Decode big images XOR
     *
     * @param path
     */
    public void xor(String path) {
        //TODO encode/decode files in given folder (XOR)
    }

}
