require 'sinatra'
require 'sinatra/reloader'
require 'sinatra/activerecord'

require_relative 'scrapyard/idea'

module Scrapyard
  class Application < Sinatra::Base
    register Sinatra::ActiveRecordExtension

    configure :development do
      set :logging, true
      register Sinatra::Reloader
    end

    get '/' do
      @ideas = Scrapyard::Idea.all
      erb :home
    end

    get '/ideas/new' do
      erb :new_idea
    end

    post '/ideas' do
      @idea = Scrapyard::Idea.new(params[:idea])
      if @idea.save
        redirect to '/'
      end
    end
  end
end
