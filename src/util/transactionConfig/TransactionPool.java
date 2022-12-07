package util.transactionConfig;

import java.util.HashMap;

public class TransactionPool {
    private HashMap<String, TransactionMVC> transactions = new HashMap<>();

    public void addTransaction(String id, TransactionMVC newTransaction) {
        this.transactions.put(id, newTransaction);
    }

    public TransactionMVC getTransaction(String id) {
        return this.transactions.get(id);
    }

}
