//
//  BaseViewController.swift
//  CSQuiz
//
//  Created by Émile Bélair on 2016-03-08.
//  Copyright © 2016 Mirego. All rights reserved.
//

import SVProgressHUD

class BaseViewController: UIViewController
{
    private var startDate: NSDate?
    
    init()
    {
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder aDecoder: NSCoder)
    {
        fatalError("init(coder:) has not been implemented")
    }
    
}

// PMARK: Loading Indicator
extension BaseViewController
{
    func showLoadingIndicator()
    {
        startDate = NSDate()
        
        SVProgressHUD.showWithMaskType(SVProgressHUDMaskType.Black)
    }
    
    func hideLoadingIndicator(success success: Bool, message: String? = nil, completion: () -> ())
    {
        var duration: NSTimeInterval = 0
        
        if let startDate = startDate
        {
            let loadingDuration = -startDate.timeIntervalSinceNow
            duration = max(0, Stylesheet.minimumLoadingDuration - loadingDuration)
        }
        
        startDate = nil
        
        delay(duration) {
            if let message = message {
                if (success) {
                    SVProgressHUD.showSuccessWithStatus(message)
                } else {
                    SVProgressHUD.showErrorWithStatus(message)
                }
            } else {
                SVProgressHUD.dismiss()
            }
            
            completion()
        }
    }
}
