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

    //ADDS ELEMENT TO DATABASE
    public abstract boolean add(E element);

    //REMOVES ELEMENT FROM DATABASE GIVEN THE PRIMARY KEY
    public abstract boolean remove(int primaryKey);

    //REMOVES ALL ELEMENTS FROM THE DATABASE
    public abstract boolean removeAll();

    //CHECKS IF THE DATABASE IS EMPTY
    public abstract boolean isEmpty();

    //UPDATES THE DATABASE GIVEN THE PRIMARYKEY, COLUMNNAME AND VALUE
    public abstract <E> void update(int primaryKey, String columnName, E value);

    //CHECKS IF AN ELEMENT GIVEN A PRIMARY KEY, EXISTS IN THE DATABASE
    public abstract boolean exists(int primaryKey);

    //RETRIEVE THE RESULTSET OF AN ELEMENT FROM A DATABASE GIVEN THE PRIMARYKEY
    public abstract ResultSet getElement(int primaryKey);

    //RETRIEVE THE RESULTSET OF ALL ELEMENTS FROM THE DATABASE
    public abstract ResultSet getAllElements();

    //GIVEN A LIST OF MAPS, DELETE ALL THE ELEMENTS OF THE DATABASE AND REBUILD
    //THE DATABASE WITH THE LIST OF MAPS.
    //(VERY INEFFICIENT, RESEARCHED USING DELTAS INSTEAD, SO THAT COULD BE A POTENTIAL UPGRADE.)
    //
    //WHERE
    //A MAP REPRESENTS A ROW IN A TABLE WHERE THE KEY = COLUMN NAME, VALUE = ELEMENT
    //THE LIST REPRESENTS THE TABLE.
    public abstract void undo(List<Map<String, Object>> resultSetListMap);

}
