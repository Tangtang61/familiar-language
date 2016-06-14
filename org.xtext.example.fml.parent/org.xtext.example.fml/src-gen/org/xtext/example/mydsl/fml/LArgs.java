/**
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.fml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>LArgs</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fml.LArgs#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.fml.FmlPackage#getLArgs()
 * @model
 * @generated
 */
public interface LArgs extends EObject
{
  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.fml.Command}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference list.
   * @see org.xtext.example.mydsl.fml.FmlPackage#getLArgs_Args()
   * @model containment="true"
   * @generated
   */
  EList<Command> getArgs();

} // LArgs