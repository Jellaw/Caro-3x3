package com.example.tictactoemvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tictactoemvvm.databinding.ActivityMainBinding;
import com.example.tictactoemvvm.model.Player;
import com.example.tictactoemvvm.viewModel.GameViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String NO_WINNER = "No One";
        GameViewModel gameViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        gameViewModel.init("P1", "P2");
        binding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();
    }
    public void setUpOnGameEndListener(){
        gameViewModel.getWinner().observe(this, new Observer<Player>() {
            @Override
            public void onChanged(Player player) {
                onWinnerChange(player);
            }
        });
    }
    public void onWinnerChange(Player winner){
        String winnerName = (winner != null &&
                (winner.name != null && !winner.name.isEmpty())) ? winner.name : NO_WINNER;
        Toast.makeText(this, "Winner is " + winnerName, Toast.LENGTH_SHORT).show();
    }
}