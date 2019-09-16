/*
Grade Calculator, Version 3
December, 2018
Ethan Vaughan

################################################################################
################################################################################

12/20/2018 - added GPA Calculator functionality.
12/20/2018 - officially finished Grade Calculator, Version 3.
05/07/2019 - allowed storing of final grades; tweaked grade calculator

################################################################################
################################################################################

 */
package gradecalculatorv3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class GradeCalculatorV3 {

    private GradeCalculatorV3() {
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
        
        // Check that the mysqld.exe (which allows connections to mysql database) is running.
        boolean result = GradeCalculatorV3.isRunning("mysqld.exe");
        if (!result) {
            Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqld.exe", null, new File("C:\\xampp\\mysql\\bin"));
            //Force the program to wait for mysql to start.
            Thread.sleep(9500);
        }
        String[] arguments = {};
        GCV3 gcv3 = new GCV3();
        gcv3.main(arguments);
    }

    public static void msgBox(String msg) {
        javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null, msg, "GradeCalculatorV3", javax.swing.JOptionPane.DEFAULT_OPTION);
    }

    // Checks if a process is running.
    public static boolean isRunning(String process) {
        boolean found = false;
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
                    + "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
                    + "Set service = locator.ConnectServer()\n"
                    + "Set processes = service.ExecQuery _\n"
                    + " (\"select * from Win32_Process where name='" + process + "'\")\n"
                    + "For Each process in processes\n"
                    + "wscript.echo process.Name \n"
                    + "Next\n"
                    + "Set WSHShell = Nothing\n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            line = input.readLine();
            if (line != null) {
                if (line.equals(process)) {
                    found = true;
                }
            }
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

}
