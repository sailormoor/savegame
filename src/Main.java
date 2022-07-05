import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress save1 = new GameProgress
                (100, 0, 1, 10);
        GameProgress save2 = new GameProgress
                (150, 2, 3, 169);
        GameProgress save3 = new GameProgress
                (1000, 16, 56, 155577);

        ArrayList<String> pathList = new ArrayList<>();
        pathList.add("C:\\Users\\Masha\\Desktop\\GamesNet\\savegames\\save1.dat");
        pathList.add("C:\\Users\\Masha\\Desktop\\GamesNet\\savegames\\save2.dat");
        pathList.add("C:\\Users\\Masha\\Desktop\\GamesNet\\savegames\\save3.dat");

        saveGames("C:\\Users\\Masha\\Desktop\\GamesNet\\savegames\\save1.dat", save1);
        saveGames("C:\\Users\\Masha\\Desktop\\GamesNet\\savegames\\save2.dat", save2);
        saveGames("C:\\Users\\Masha\\Desktop\\GamesNet\\savegames\\save3.dat", save3);

        zipFiles("C:\\Users\\Masha\\Desktop\\GamesNet\\savegames\\zip.zip", pathList);

    }

    static void saveGames(String path, GameProgress gm) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gm);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void zipFiles(String pathZip, ArrayList<String> pathDat) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(pathZip))) {
            int i = 1;
            for (String pathDatZip : pathDat) {
                try (FileInputStream fis = new FileInputStream(pathDatZip)) {
                    ZipEntry entry = new ZipEntry("save " + i);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                    i++;


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

                File file = new File(pathDatZip);
                file.delete();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
