package util.transactionConfig;

import view.BaseView;

public class TransactionConector{
    public static void connectTransactionElements(TransactionMVC transaccion) {
        transaccion.getController().setModel(transaccion.getModel());
        for (BaseView view : transaccion.getViews()) {
            transaccion.getModel().addView(view);
        }
    }

}
