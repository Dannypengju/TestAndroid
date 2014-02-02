
package com.example.testandroid;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Rotate3D extends Animation {
    private float mFromDegree;

    private float mToDegree;

    private float mCenterX;

    private float mCenterY;

    private float mLeft;

    private float mTop;

    private Camera mCamera;

    private static final String TAG = "Rotate3d";

    /**
     * Creates a new 3D rotation on the Y axis. The rotation is defined by its
     * start angle and its end angle. Both angles are in degrees. The rotation
     * is performed around a center point on the 2D space, definied by a pair of
     * X and Y coordinates, called centerX and centerY. When the animation
     * starts, a translation on the Z axis (depth) is performed. The length of
     * the translation can be specified, as well as whether the translation
     * should be reversed in time.
     * 
     * @param fromDegrees the start angle of the 3D rotation
     * @param toDegrees the end angle of the 3D rotation
     * @param centerX the X center of the 3D rotation
     * @param centerY the Y center of the 3D rotation
     * @param reverse true if the translation should be reversed, false
     *            otherwise
     */
    public Rotate3D(float fromDegree, float toDegree, float left, float top, float centerX,
            float centerY) {
        this.mFromDegree = fromDegree;
        this.mToDegree = toDegree;
        this.mLeft = left;
        this.mTop = top;
        this.mCenterX = centerX;
        this.mCenterY = centerY;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float FromDegree = mFromDegree;
        float degrees = FromDegree + (mToDegree - mFromDegree) * interpolatedTime;
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Matrix matrix = t.getMatrix();

        /*if (degrees <= -76.0f) {
            degrees = -90.0f;
            mCamera.save();
            mCamera.rotateY(degrees);
            mCamera.getMatrix(matrix);
            mCamera.restore();
        } else if (degrees >= 76.0f) {
            degrees = 90.0f;
            mCamera.save();
            mCamera.rotateY(degrees);
            mCamera.getMatrix(matrix);
            mCamera.restore();
        } else {
            mCamera.save();

            mCamera.rotateY(degrees);
            mCamera.translate(0, 0, -centerX);
            mCamera.getMatrix(matrix);
            mCamera.restore();
        }*/
        mCamera.save();
        mCamera.translate(0, 0, 0);
        mCamera.rotateY(degrees);
        mCamera.getMatrix(matrix);
        mCamera.restore();
        
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }

}
