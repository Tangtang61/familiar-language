/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.SetOperation#getSop <em>Sop</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.SetOperation#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getSetOperation()
 * @model
 * @generated
 */
public interface SetOperation extends ComplexCommand
{
  /**
   * Returns the value of the '<em><b>Sop</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.fml.SetOperator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sop</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sop</em>' attribute.
   * @see org.xtext.example.mydsl.fml.SetOperator
   * @see #setSop(SetOperator)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSetOperation_Sop()
   * @model
   * @generated
   */
  SetOperator getSop();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.SetOperation#getSop <em>Sop</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sop</em>' attribute.
   * @see org.xtext.example.mydsl.fml.SetOperator
   * @see #getSop()
   * @generated
   */
  void setSop(SetOperator value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Right</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' containment reference.
   * @see #setRight(ComplexCommand)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSetOperation_Right()
   * @model containment="true"
   * @generated
   */
  ComplexCommand getRight();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.SetOperation#getRight <em>Right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' containment reference.
   * @see #getRight()
   * @generated
   */
  void setRight(ComplexCommand value);

} // SetOperation