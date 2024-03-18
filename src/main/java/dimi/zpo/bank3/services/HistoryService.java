package dimi.zpo.bank3.services;

import dimi.zpo.bank3.entities.TransferEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class HistoryService {
    public static List<TransferEntity> sortByField(List<TransferEntity> list, String fieldName, Boolean direction) {
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
    }
}
