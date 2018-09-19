package ac.daffodil.controller;

import ac.daffodil.dao.FinancialDao;
import ac.daffodil.dao.OrganizationDao;
import ac.daffodil.model.Financial;
import ac.daffodil.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by Muiduzzaman Lipu on 03-Apr-18.
 */
@Controller
@RequestMapping("/admin")
public class adminDashController {

    String cashLabel[] = {"Collection Cash","Total Payment Cash","Balance Cash"};
    List<Long> cashPoint = new ArrayList<Long>();
    String bankLabel[] = {"Collection Bank","Total Payment Bank","Balance Bank"};
    List<Long> bankPoint = new ArrayList<Long>();
    List<String> organizationLabel;
    List<Long> organizationPoint;

    @Autowired
    FinancialDao financialDao;

    @Autowired
    OrganizationDao organizationDao;

    @RequestMapping(value = { "/adminDashPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",  request.getParameter("message"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Financial finan : financialDao.sumAllFieldByDate()) {
            if(format.format(finan.getLastUpdateDate()).equals(format.format(new Date()))){
                cashPoint.add(finan.getCollectionCash());
                cashPoint.add(finan.getTotalPaymentCash());
                cashPoint.add(finan.getBalanceCash());

                bankPoint.add(finan.getCollectionBank());
                bankPoint.add(finan.getTotalPaymentBank());
                bankPoint.add(finan.getBalanceBank());
            }
        }

        organizationLabel = new ArrayList<String>();
        organizationPoint = new ArrayList<Long>();

        for (Financial fin : financialDao.getAll()) {
            if(format.format(fin.getLastUpdateDate()).equals(format.format(new Date()))){
                for(Organization organization : organizationDao.getAll()){
                    if(organization.getOrganizationId() == fin.getOrganizationId()){
                        organizationLabel.add(organization.getOrganizationName());
                        organizationPoint.add(fin.getAccountsReceivable());
                    }
                }
            }
        }

        modelAndView.addObject("cashLabel", cashLabel);
        modelAndView.addObject("cashPoint", cashPoint);
        modelAndView.addObject("bankLabel", bankLabel);
        modelAndView.addObject("bankPoint", bankPoint);
        modelAndView.addObject("organizationLabel", organizationLabel);
        modelAndView.addObject("organizationPoint", organizationPoint);

        modelAndView.setViewName("admin/adminDash");
        return modelAndView;
    }
}
