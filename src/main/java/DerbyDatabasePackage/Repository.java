/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DerbyDatabasePackage;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Caleb
 * @param <E>
 */
public interface Repository<E extends Comparable<E>> {

    public void add(E element);

    public boolean remove(int primaryKey);

    public boolean removeAll();

    public boolean isEmpty();

    public <E> void update(int primaryKey, String columnName, E value);

    public boolean exists(int primaryKey);

    public ResultSet getElement(int primaryKey);

    public ResultSet getAllElements();

    public void undo(List<Map<String, Object>> resultSetListMap);

}
