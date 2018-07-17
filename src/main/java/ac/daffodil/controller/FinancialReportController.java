package ac.daffodil.controller;

import ac.daffodil.dao.FinancialDao;
import ac.daffodil.dao.OrganizationDao;
import ac.daffodil.model.Financial;
import ac.daffodil.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@RestController
public class FinancialReportController {

    @Autowired
    FinancialDao financialDao;

    @Autowired
    OrganizationDao organizationDao;

    List<Financial> financialsByDate= new ArrayList<Financial>();

    @RequestMapping(value = { "/admin/financialReport/financialReportPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminFinancialReport");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/financialReport/getAllOrganization", method = RequestMethod.GET)
    public List<Organization> getAllOrganization() {
        return organizationDao.getAll();
    }

    @RequestMapping(value = "/admin/financialReport/findFinancial", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<Financial> findFinancial(@RequestBody Long id) {
        List<Financial> financialsByOId= financialDao.findByOrganizationId(id);
        return financialsByOId;
    }

//    @RequestMapping(value = "/admin/financialReport/findAllFinancialToday", method = RequestMethod.GET)
//    public Financial findAllFinancialToday() {
//        List<Financial> financials = financialDao.getAll();
//        Financial returnFinancial = new Financial();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Long collectionCash=0L;
//        Long collectionBank=0L;
//        Long totalPaymentCash=0L;
//        Long totalPaymentBank=0L;
//        Long balanceBank=0L;
//        Long balanceCash=0L;
//        Long accountsReceivable=0L;
//        Long accountsPayable=0L;
//        Long ondateAdmitedStudents=0L;
//        Long newStudents=0L;
//        for (Financial financial:financials) {
//            if(simpleDateFormat.format(financial.getLastUpdateDate()).equals(simpleDateFormat.format(new Date()))){
//                collectionCash = collectionCash + financial.getCollectionCash();
//                collectionBank = collectionBank + financial.getCollectionBank();
//                totalPaymentCash = totalPaymentCash + financial.getTotalPaymentCash();
//                totalPaymentBank = totalPaymentBank + financial.getTotalPaymentBank();
//                balanceBank = balanceBank + financial.getBalanceBank();
//                balanceCash = balanceCash + financial.getBalanceCash();
//                accountsReceivable = accountsReceivable + financial.getAccountsReceivable();
//                accountsPayable = accountsPayable + financial.getAccountsPayable();
//                ondateAdmitedStudents = ondateAdmitedStudents + financial.getOndateAdmitedStudents();
//                newStudents = newStudents + financial.getNewStudents();
//            }
//        }
//        returnFinancial.setCollectionCash(collectionCash);
//        returnFinancial.setCollectionBank(collectionBank);
//        returnFinancial.setTotalPaymentCash(totalPaymentCash);
//        returnFinancial.setTotalPaymentBank(totalPaymentBank);
//        returnFinancial.setBalanceBank(balanceBank);
//        returnFinancial.setBalanceCash(balanceCash);
//        returnFinancial.setAccountsReceivable(accountsReceivable);
//        returnFinancial.setAccountsPayable(accountsPayable);
//        returnFinancial.setOndateAdmitedStudents(ondateAdmitedStudents);
//        returnFinancial.setNewStudents(newStudents);
//        return returnFinancial;
//    }

    @RequestMapping(value = { "/admin/financialReport/financialReportAllPage" }, method = RequestMethod.GET)
    public ModelAndView indexReportAll(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminFinancialReportAll");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/financialReport/sumAllFieldByDate", method = RequestMethod.GET)
    public List<Financial> sumAllFieldByDate() {
        return financialDao.sumAllFieldByDate();
    }

}
