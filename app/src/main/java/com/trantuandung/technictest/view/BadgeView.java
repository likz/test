package com.trantuandung.technictest.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import com.trantuandung.technictest.R;

/**
 * A simple text label view that can be applied as a "badge"
 */
public class BadgeView extends TextView {

    private static final int POSITION_TOP_LEFT = 1;
    private static final int POSITION_TOP_RIGHT = 2;
    private static final int POSITION_BOTTOM_LEFT = 3;
    private static final int POSITION_BOTTOM_RIGHT = 4;
    private static final int POSITION_CENTER = 5;
    private static final int DEFAULT_POSITION = POSITION_TOP_RIGHT;

    private static final int DEFAULT_MARGIN_DIP = 0;
    private static final int DEFAULT_LR_PADDING_DIP = 8;
    private static final int DEFAULT_CORNER_RADIUS_DIP = 100;

    private Context context;
    private View target;

    private int badgePosition;
    private int badgeMarginH;
    private int badgeMarginV;
    private int badgeColor;

    private boolean isShown;

    private ShapeDrawable badgeBg;


    public BadgeView(Context context) {
	super(context, null, android.R.attr.textViewStyle);
	init(context, null);
    }


    public BadgeView(Context context, View target) {
	super(context, null, android.R.attr.textViewStyle);
	init(context, target);
    }

    private void init(Context context, View target) {

	this.context = context;
	this.target = target;

	// apply defaults
	badgePosition = DEFAULT_POSITION;
	badgeMarginH =dipToPixels(context, DEFAULT_MARGIN_DIP);
	badgeMarginV = badgeMarginH;
	badgeColor = ContextCompat.getColor(context, R.color.badge_view);

	setTypeface(Typeface.DEFAULT_BOLD);
	int paddingPixels = dipToPixels(context, DEFAULT_LR_PADDING_DIP);
	setPadding(paddingPixels, paddingPixels / 2 - 1, paddingPixels, paddingPixels / 2 - 1);
	setTextColor(Color.WHITE);

	isShown = false;

	if (this.target != null) {
	    applyTo(this.target);
	} else {
	    show();
	}

    }

    private void applyTo(View target) {

	ViewGroup.LayoutParams lp = target.getLayoutParams();
	ViewParent parent = target.getParent();
	FrameLayout container = new FrameLayout(context);

	if (target instanceof TabWidget) {

	    // set target to the relevant tab child container
	    target = ((TabWidget) target).getChildTabViewAt(0);
	    this.target = target;

	    ((ViewGroup) target).addView(container,
		    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

	    this.setVisibility(View.GONE);
	    container.addView(this);

	} else {
	    boolean vGroup = false;
	    ViewGroup group = null;
	    if (parent instanceof ViewGroup) {
		group = (ViewGroup) parent;
		int index = group.indexOfChild(target);

		group.removeView(target);
		group.addView(container, index, lp);
		vGroup = true;
	    }

	    container.addView(target);

	    this.setVisibility(View.GONE);
	    container.addView(this);

	    if (vGroup)
		group.invalidate();

	}

    }

    /**
     * Make the badge visible in the UI.
     */
    public void show() {
	if (getBackground() == null) {
	    if (badgeBg == null) {
		badgeBg = getDefaultBackground();
	    }
	    setBackground(badgeBg);
	}
	applyLayoutParams();
	this.setVisibility(View.VISIBLE);
	isShown = true;
    }

    private ShapeDrawable getDefaultBackground() {

	int r = dipToPixels(context, DEFAULT_CORNER_RADIUS_DIP);
	float[] outerR = new float[]{r, r, r, r, r, r, r, r};

	RoundRectShape rr = new RoundRectShape(outerR, null, null);
	ShapeDrawable drawable = new ShapeDrawable(rr);
	drawable.getPaint().setColor(badgeColor);

	return drawable;

    }

    private void applyLayoutParams() {

	FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

	switch (badgePosition) {
	    case POSITION_TOP_LEFT:
		lp.gravity = Gravity.START | Gravity.TOP;
		lp.setMargins(badgeMarginH, badgeMarginV, 0, 0);
		break;
	    case POSITION_TOP_RIGHT:
		lp.gravity = Gravity.END | Gravity.TOP;
		lp.setMargins(0, badgeMarginV, badgeMarginH, 0);
		break;
	    case POSITION_BOTTOM_LEFT:
		lp.gravity = Gravity.START | Gravity.BOTTOM;
		lp.setMargins(badgeMarginH, 0, 0, badgeMarginV);
		break;
	    case POSITION_BOTTOM_RIGHT:
		lp.gravity = Gravity.END | Gravity.BOTTOM;
		lp.setMargins(0, 0, badgeMarginH, badgeMarginV);
		break;
	    case POSITION_CENTER:
		lp.gravity = Gravity.CENTER;
		lp.setMargins(0, 0, 0, 0);
		break;
	    default:
		break;
	}

	setLayoutParams(lp);

    }

    /**
     * Is this badge currently visible in the UI?
     */
    @Override
    public boolean isShown() {
	return isShown;
    }


    /**
     * Set the horizontal margin from the target View that is applied to this badge.
     *
     * @param horizontal margin in pixels.
     */
    public void setBadgeMarginHorizontal(int horizontal) {
	this.badgeMarginH = horizontal;
    }

	private int dipToPixels(Context c, int dip) {
		Resources r = c.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
		return (int) px;
	}
}
