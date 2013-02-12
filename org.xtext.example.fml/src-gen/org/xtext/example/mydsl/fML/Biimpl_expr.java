/**
 */
package org.xtext.example.mydsl.fML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Biimpl expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.Biimpl_expr#getLeft <em>Left</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fML.Biimpl_expr#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.mydsl.fML.FMLPackage#getBiimpl_expr()
 * @model
 * @generated
 */
public interface Biimpl_expr extends CNFExpression
{
  /**
   * Returns the value of the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Left</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left</em>' containment reference.
   * @see #setLeft(CNFExpression)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getBiimpl_expr_Left()
   * @model containment="true"
   * @generated
   */
  CNFExpression getLeft();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Biimpl_expr#getLeft <em>Left</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left</em>' containment reference.
   * @see #getLeft()
   * @generated
   */
  void setLeft(CNFExpression value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Right</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' containment reference.
   * @see #setRight(CNFExpression)
   * @see org.xtext.example.mydsl.fML.FMLPackage#getBiimpl_expr_Right()
   * @model containment="true"
   * @generated
   */
  CNFExpression getRight();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fML.Biimpl_expr#getRight <em>Right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' containment reference.
   * @see #getRight()
   * @generated
   */
  void setRight(CNFExpression value);

} // Biimpl_expr