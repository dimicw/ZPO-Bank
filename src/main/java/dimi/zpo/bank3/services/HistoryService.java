package dimi.zpo.bank3.services;

import dimi.zpo.bank3.entities.HistoryEntry;
import dimi.zpo.bank3.entities.TransferEntity;
import dimi.zpo.bank3.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<HistoryEntry> entries = new ArrayList<>();

        List<String> accountNumbers = new ArrayList<>();    //TODO
        accountNumbers.add("1234");

        for (String accountNumber : accountNumbers) {
            String accountType = "test";    //TODO
            List<TransferEntity> transfers = transferRepository.findByFromAccountOrToAccount(accountNumber, accountNumber);

            for (TransferEntity transfer : transfers) {
                String type, fromAccount, toAccount;

                if (transfer.getFromAccount() == null) {
                    type = "Deposit";
                    fromAccount = "-";
                    toAccount = accountType;
                } else if (transfer.getFromAccount().equals(accountNumber)) {
                    fromAccount = accountType;
                    if (transfer.getToAccount() == null) {
                        type = "Withdrawal";
                        toAccount = "-";
                    } else {
                        type = "Outgoing transfer";
                        toAccount = transfer.getToAccount();
                    }
                } else {
                    type = "Incoming transfer";
                    fromAccount = transfer.getFromAccount();
                    toAccount = accountType;
                }
                entries.add(new HistoryEntry(transfer.getDate(), type, fromAccount, toAccount, transfer.getAmount()));
            }
        }
        return sortByField(entries, field, direction);
    }

    @Autowired
    private TransferRepository transferRepository;
}
