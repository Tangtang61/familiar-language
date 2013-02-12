/**
 */
package org.xtext.example.mydsl.fML;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Slice Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.fML.FMLPackage#getSliceMode()
 * @model
 * @generated
 */
public enum SliceMode implements Enumerator
{
  /**
   * The '<em><b>INCLUDING</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INCLUDING_VALUE
   * @generated
   * @ordered
   */
  INCLUDING(0, "INCLUDING", "including"),

  /**
   * The '<em><b>EXCLUDING</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EXCLUDING_VALUE
   * @generated
   * @ordered
   */
  EXCLUDING(1, "EXCLUDING", "excluding");

  /**
   * The '<em><b>INCLUDING</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>INCLUDING</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INCLUDING
   * @model literal="including"
   * @generated
   * @ordered
   */
  public static final int INCLUDING_VALUE = 0;

  /**
   * The '<em><b>EXCLUDING</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EXCLUDING</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EXCLUDING
   * @model literal="excluding"
   * @generated
   * @ordered
   */
  public static final int EXCLUDING_VALUE = 1;

  /**
   * An array of all the '<em><b>Slice Mode</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final SliceMode[] VALUES_ARRAY =
    new SliceMode[]
    {
      INCLUDING,
      EXCLUDING,
    };

  /**
   * A public read-only list of all the '<em><b>Slice Mode</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<SliceMode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Slice Mode</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SliceMode get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      SliceMode result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Slice Mode</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SliceMode getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      SliceMode result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Slice Mode</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SliceMode get(int value)
  {
    switch (value)
    {
      case INCLUDING_VALUE: return INCLUDING;
      case EXCLUDING_VALUE: return EXCLUDING;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private SliceMode(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //SliceMode