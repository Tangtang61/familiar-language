/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Union Or Intersection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.SetUnionOrIntersection#getOp <em>Op</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.SetUnionOrIntersection#getSetl <em>Setl</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.SetUnionOrIntersection#getSetr <em>Setr</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getSetUnionOrIntersection()
 * @model
 * @generated
 */
public interface SetUnionOrIntersection extends SetCommand, SetOperations
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSetUnionOrIntersection_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.SetUnionOrIntersection#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Setl</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Setl</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Setl</em>' containment reference.
   * @see #setSetl(SetCommand)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSetUnionOrIntersection_Setl()
   * @model containment="true"
   * @generated
   */
  SetCommand getSetl();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.SetUnionOrIntersection#getSetl <em>Setl</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Setl</em>' containment reference.
   * @see #getSetl()
   * @generated
   */
  void setSetl(SetCommand value);

  /**
   * Returns the value of the '<em><b>Setr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Setr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Setr</em>' containment reference.
   * @see #setSetr(SetCommand)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getSetUnionOrIntersection_Setr()
   * @model containment="true"
   * @generated
   */
  SetCommand getSetr();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.SetUnionOrIntersection#getSetr <em>Setr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Setr</em>' containment reference.
   * @see #getSetr()
   * @generated
   */
  void setSetr(SetCommand value);

} // SetUnionOrIntersection