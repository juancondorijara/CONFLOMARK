/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author jesus
 */
public class UtilToSql {

    public static java.sql.Timestamp convert(java.util.Date uDate) {
        Timestamp timestamp = new Timestamp(uDate.getTime());
        return timestamp;
    }
}
