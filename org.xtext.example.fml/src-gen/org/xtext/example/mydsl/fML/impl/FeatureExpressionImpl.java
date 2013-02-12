/**
 */
package org.xtext.example.mydsl.fML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.xtext.example.mydsl.fML.FMLPackage;
import org.xtext.example.mydsl.fML.FeatureExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.fML.impl.FeatureExpressionImpl#getFt <em>Ft</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureExpressionImpl extends MinimalEObjectImpl.Container implements FeatureExpression
{
  /**
   * The cached value of the '{@link #getFt() <em>Ft</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFt()
   * @generated
   * @ordered
   */
  protected EObject ft;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeatureExpressionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return FMLPackage.eINSTANCE.getFeatureExpression();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getFt()
  {
    return ft;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFt(EObject newFt, NotificationChain msgs)
  {
    EObject oldFt = ft;
    ft = newFt;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMLPackage.FEATURE_EXPRESSION__FT, oldFt, newFt);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFt(EObject newFt)
  {
    if (newFt != ft)
    {
      NotificationChain msgs = null;
      if (ft != null)
        msgs = ((InternalEObject)ft).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMLPackage.FEATURE_EXPRESSION__FT, null, msgs);
      if (newFt != null)
        msgs = ((InternalEObject)newFt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMLPackage.FEATURE_EXPRESSION__FT, null, msgs);
      msgs = basicSetFt(newFt, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FMLPackage.FEATURE_EXPRESSION__FT, newFt, newFt));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FMLPackage.FEATURE_EXPRESSION__FT:
        return basicSetFt(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case FMLPackage.FEATURE_EXPRESSION__FT:
        return getFt();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FMLPackage.FEATURE_EXPRESSION__FT:
        setFt((EObject)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case FMLPackage.FEATURE_EXPRESSION__FT:
        setFt((EObject)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case FMLPackage.FEATURE_EXPRESSION__FT:
        return ft != null;
    }
    return super.eIsSet(featureID);
  }

} //FeatureExpressionImpl