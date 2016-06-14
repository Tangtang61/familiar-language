/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Remove Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.RemoveConstraint#getCst <em>Cst</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.fml.RemoveConstraint#getFm <em>Fm</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getRemoveConstraint()
 * @model
 * @generated
 */
public interface RemoveConstraint extends Command
{
  /**
   * Returns the value of the '<em><b>Cst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cst</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cst</em>' containment reference.
   * @see #setCst(ConstraintCommand)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getRemoveConstraint_Cst()
   * @model containment="true"
   * @generated
   */
  ConstraintCommand getCst();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.RemoveConstraint#getCst <em>Cst</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cst</em>' containment reference.
   * @see #getCst()
   * @generated
   */
  void setCst(ConstraintCommand value);

  /**
   * Returns the value of the '<em><b>Fm</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fm</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fm</em>' containment reference.
   * @see #setFm(FMCommand)
   * @see org.xtext.example.mydsl.fml.FmlPackage#getRemoveConstraint_Fm()
   * @model containment="true"
   * @generated
   */
  FMCommand getFm();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.fml.RemoveConstraint#getFm <em>Fm</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fm</em>' containment reference.
   * @see #getFm()
   * @generated
   */
  void setFm(FMCommand value);

} // RemoveConstraint