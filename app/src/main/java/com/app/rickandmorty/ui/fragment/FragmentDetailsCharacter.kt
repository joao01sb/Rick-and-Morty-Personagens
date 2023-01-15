package com.app.rickandmorty.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.app.rickandmorty.databinding.FragmentDetailsCharacterBinding
import com.app.rickandmorty.domain.viewModel.CharacterViewModel
import com.app.rickandmorty.extras.loadImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FragmentDetailsCharacter : Fragment() {

    private val args: FragmentDetailsCharacterArgs by navArgs()
    private lateinit var binding: FragmentDetailsCharacterBinding
    private val characterViewModel: CharacterViewModel by viewModel { parametersOf(args.personagem) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            nome.text = characterViewModel.character.name ?: ""
            genero.text = characterViewModel.character.gender ?: ""
            status.text = characterViewModel.character.status ?: ""
            especie.text = characterViewModel.character.species ?: ""
            origem.text = characterViewModel.character.origin?.name ?: ""
            characterViewModel.character.image?.let { imagemPersonagem.loadImage(it) }
            salvarPersonagem.setOnClickListener {
                try {
                    lifecycleScope.launch(Dispatchers.IO) {
                        characterViewModel.saveCharacter(args.personagem)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                "Save Character Sucess!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        requireContext(),
                        "Character are save!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}