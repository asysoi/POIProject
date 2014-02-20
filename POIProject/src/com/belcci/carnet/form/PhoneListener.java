package com.belcci.carnet.form;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

public class PhoneListener implements VerifyListener {

	public void verifyText(VerifyEvent event) {
		switch (event.keyCode) {
		case SWT.BS:    // Backspace
		case SWT.DEL:   // Delete
		case SWT.HOME:  // Home
		case SWT.END:   // End
		case SWT.ARROW_LEFT:  // Left arrow
		case SWT.ARROW_RIGHT: // Right arrow
			return;
		}

		for (char c : event.text.toCharArray())
			if (!((Character.isDigit(c) || c == '(' || c == ')' || c == '+'))) {
				event.doit = false; 
				return;
			}
	}
}
