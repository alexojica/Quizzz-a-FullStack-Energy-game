package client.utils;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class AvatarSupplier{

    private static final String FILE_PATH = "client/src/main/resources/images/avatar/";
    private static String userDirectory = Paths.get("")
            .toAbsolutePath() + "/";

    public static Path generateAvatar(Avatar avatar, String name, Path filePath) {
        return generateAvatar(avatar, 1, 1, name, filePath);
    }

    public static Path generateAvatar(Avatar avatar, int w, int h, String fileName, Path filePath) {
        int size = avatar.getWidth();
        BufferedImage dest = new BufferedImage(size * w, size * h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);

        int seed = generateSeed(fileName);
        Random random = new Random(seed);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                long code = Math.abs(random.nextLong());
                g2.drawImage(avatar.create(code), x * size, y * size, size, size, null);
                //g2.setColor(Color.RED);
                //g2.drawRect(x * 128, y * 128, 128, 128);
            }
            System.out.println("");
        }

        g2.dispose();

       return generateImage(dest,fileName,filePath);
    }

    /**
     * Method that manually deletes all avatars saved so far
     */

    public static void clearAllAvatars() {
        Path currentPath = Paths.get(FILE_PATH);
        String finalPath = userDirectory + currentPath.toString();
        File dir = new File(finalPath);
        File[] savedAvatars = dir.listFiles();
        for (int i = 0; i < savedAvatars.length; i++)
        {
            File current = savedAvatars[i];
            current.delete();
        }
    }

    /**
     * Mathod that manually generates an integer seed from a given name
     * @param name
     * @return
     */

    private static int generateSeed(String name)
    {
        char[] nameArr = name.toCharArray();
        int result = 0;
        for(int i = 0; i < nameArr.length; i++)
        {
            result += nameArr[i];
        }
        return result;
    }

    /**
     * Mathod that generates a new BufferedImage with given name and saves it in the finalPath
     * which might look something like "C:\OOPP\Project\repository-template\client\src\main\resources\images\avatar\Simi3271836267810498327.png"
     * The temporary images created should automatically delete after the program exits (rn it doesn't seem to work idk why)
     * @param bi
     * @param name
     * @return
     */
    public static Path generateImage(BufferedImage bi, String name, Path existingFilePath) {
        try {
            Path currentPath = Paths.get(FILE_PATH);
            String finalPath = userDirectory + currentPath.toString();

            try {
                if(existingFilePath == null || existingFilePath.toFile().isFile() == false)
                {
                    //create it if the file does not exist
                    Path file = Files.createTempFile(Paths.get(finalPath), name, ".png");

                    file.toFile().deleteOnExit();
                    ImageIO.write(bi, "png", file.toFile());

                    return file;
                }
                else
                {
                    File checkFile = existingFilePath.toFile();
                    ImageIO.write(bi, "png", checkFile);

                    System.out.println("Changing existing avatar image");

                    return existingFilePath;
                }

            }catch (IOException e) {
                System.out.println("Error creating file");
                return  null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
