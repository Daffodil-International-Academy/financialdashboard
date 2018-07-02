package ac.daffodil.controller;

import ac.daffodil.dao.FinancialDao;
import ac.daffodil.dao.OrganizationDao;
import ac.daffodil.model.Financial;
import ac.daffodil.model.Organization;
import ac.daffodil.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@RestController
public class CenterHeadDashController {

    @Autowired
    FinancialDao financialDao;

    @Autowired
    OrganizationDao organizationDao;

    List<Financial> financialsByDate= new ArrayList<Financial>();

    @RequestMapping(value = { "/centerHead/centerHeadDashPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("centerHead/centerHeadDash");
        return modelAndView;
    }

    @RequestMapping(value = { "/centerHead/financialReport/financialReportPage" }, method = RequestMethod.GET)
    public ModelAndView financialReportPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("centerHead/centerHeadFinancialReport");
        return modelAndView;
    }

    @RequestMapping(value = "/centerHead/financialReport/getAllOrganization", method = RequestMethod.GET)
    public List<Organization> getAllOrganization() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentLoggedUser = (User) authentication.getPrincipal();
        List<Organization> organizationList = new ArrayList<Organization>();
        for (Organization organization : organizationDao.getAll()) {
            if(organization.getOrganizationId().equals(currentLoggedUser.getOrganizationId())){
                organizationList.add(organization);
            }
        }
        return organizationList;
    }

    @RequestMapping(value = "/centerHead/financialReport/findFinancial", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<Financial> findFinancial(@RequestBody Long id) {
        List<Financial> financialsByOId= financialDao.findByOrganizationId(id);
        return financialsByOId;
    }

    @RequestMapping(value = "/centerHead/financialReport/findAllFinancialToday", method = RequestMethod.GET)
    public Financial findAllFinancialToday() {
        List<Financial> financials = financialDao.getAll();
        Financial returnFinancial = new Financial();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Long collectionCash=0L;
        Long collectionBank=0L;
        Long totalPaymentCash=0L;
        Long totalPaymentBank=0L;
        Long balanceBank=0L;
        Long balanceCash=0L;
        Long accountsReceivable=0L;
        Long accountsPayable=0L;
        Long ondateAdmitedStudents=0L;
        Long newStudents=0L;
        for (Financial financial:financials) {
            if(simpleDateFormat.format(financial.getLastUpdateDate()).equals(simpleDateFormat.format(new Date()))){
                collectionCash = collectionCash + financial.getCollectionCash();
                collectionBank = collectionBank + financial.getCollectionBank();
                totalPaymentCash = totalPaymentCash + financial.getTotalPaymentCash();
                totalPaymentBank = totalPaymentBank + financial.getTotalPaymentBank();
                balanceBank = balanceBank + financial.getBalanceBank();
                balanceCash = balanceCash + financial.getBalanceCash();
                accountsReceivable = accountsReceivable + financial.getAccountsReceivable();
                accountsPayable = accountsPayable + financial.getAccountsPayable();
                ondateAdmitedStudents = ondateAdmitedStudents + financial.getOndateAdmitedStudents();
                newStudents = newStudents + financial.getNewStudents();
            }
        }
        returnFinancial.setCollectionCash(collectionCash);
        returnFinancial.setCollectionBank(collectionBank);
        returnFinancial.setTotalPaymentCash(totalPaymentCash);
        returnFinancial.setTotalPaymentBank(totalPaymentBank);
        returnFinancial.setBalanceBank(balanceBank);
        returnFinancial.setBalanceCash(balanceCash);
        returnFinancial.setAccountsReceivable(accountsReceivable);
        returnFinancial.setAccountsPayable(accountsPayable);
        returnFinancial.setOndateAdmitedStudents(ondateAdmitedStudents);
        returnFinancial.setNewStudents(newStudents);
        return returnFinancial;
    }

    @RequestMapping(value = { "/centerHead/financialReport/financialReportAllPage" }, method = RequestMethod.GET)
    public ModelAndView financialReportAllPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("centerHead/centerHeadFinancialReportAll");
        return modelAndView;
    }

}
