package com.sossolution.serviceonway.Class;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public  abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager layoutManager;

    /**
     * Supporting only LinearLayoutManager for now.
     *
     * @param layoutManager
     */
    public PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;

    }
}
