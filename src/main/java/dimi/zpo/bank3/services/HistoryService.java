package dimi.zpo.bank3.services;

import dimi.zpo.bank3.entities.AccountEntity;
import dimi.zpo.bank3.entities.AccountTypeEntity;
import dimi.zpo.bank3.entities.HistoryEntry;
import dimi.zpo.bank3.entities.TransferEntity;
import dimi.zpo.bank3.repositories.AccountRepository;
import dimi.zpo.bank3.repositories.AccountTypeRepository;
import dimi.zpo.bank3.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class HistoryService {

    /*public static List<TransferEntity> sortByField(List<TransferEntity> list, String fieldName, Boolean direction) {
        Comparator<TransferEntity> comparator = (o1, o2) -> {
            int result;
            switch (fieldName) {
                case "date":
                    result = o1.getDate().compareTo(o2.getDate());
                    break;
                case "amount":
                    result = o1.getAmount().compareTo(o2.getAmount());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name: " + fieldName);
            }
            return direction ? -result : result;
        };

        Collections.sort(list, comparator);
        return list;
    }*/
    public static List<HistoryEntry> sortByField(List<HistoryEntry> list, String fieldName, Boolean direction) {
        Comparator<HistoryEntry> comparator = (o1, o2) -> {
            int result;
            switch (fieldName) {
                case "date":
                    result = o1.getDate().compareTo(o2.getDate());
                    break;
                case "amount":
                    result = o1.getAmount().compareTo(o2.getAmount());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name: " + fieldName);
            }
            return direction ? -result : result;
        };

        Collections.sort(list, comparator);
        return list;
    }

    public List<HistoryEntry> generateEntries(String field, Boolean direction) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<AccountEntity> accounts = accountRepository.findByOwnerId(auth.getName());
        List<String> accountNumbers = new ArrayList<>();
        for (AccountEntity account : accounts) accountNumbers.add(account.getNumber());

        List<HistoryEntry> entries = new ArrayList<>();

        for (AccountEntity account : accounts) {
            String accountType = accountTypeRepository.findById(account.getAccountType()).get(0).getName();
            List<TransferEntity> transfers = transferRepository.findByFromAccountOrToAccount(account.getNumber(), account.getNumber());

            for (TransferEntity transfer : transfers) {
                String type = "", fromAccount = "", toAccount = "";
                Boolean isInternal = accountNumbers.contains(transfer.getFromAccount()) && accountNumbers.contains(transfer.getToAccount());

                if (transfer.getFromAccount() == null) {
                    type = "Deposit";
                    fromAccount = "-";
                    toAccount = accountType;
                } else if (transfer.getFromAccount().equals(account.getNumber())) {
                    fromAccount = accountType;

                    if(isInternal) {
                        for (AccountEntity account2 : accounts)
                            if (transfer.getToAccount() != null)
                                if (transfer.getToAccount().equals(account2.getNumber()))
                                    toAccount = accountTypeRepository.findById(account2.getAccountType()).get(0).getName();
                        type = "Internal transfer";
                    } else {
                        if (transfer.getToAccount() == null) {
                            type = "Withdrawal";
                            toAccount = "-";
                        } else {
                            type = "Outgoing transfer";
                            toAccount = transfer.getToAccount();
                        }
                    }
                } else if (!isInternal){
                    type = "Incoming transfer";
                    fromAccount = transfer.getFromAccount();
                    toAccount = accountType;
                }

                if(type != "")
                    entries.add(new HistoryEntry(transfer.getDate(), type, fromAccount, toAccount, transfer.getAmount()));
            }
        }
        return sortByField(entries, field, direction);
    }

    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
}
